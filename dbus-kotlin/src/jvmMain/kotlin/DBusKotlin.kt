package pl.mareklangiewicz.dbuskotlin

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.freedesktop.IScreenSaver
import org.freedesktop.dbus.Struct
import org.freedesktop.dbus.Tuple
import org.freedesktop.dbus.bin.DBusDaemon
import org.freedesktop.dbus.connections.AbstractConnection
import org.freedesktop.dbus.connections.impl.DBusConnection
import org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType.SESSION
import org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType.SYSTEM
import org.freedesktop.dbus.exceptions.DBusExecutionException
import org.freedesktop.dbus.interfaces.CallbackHandler
import org.freedesktop.dbus.interfaces.DBusInterface
import org.freedesktop.dbus.interfaces.Introspectable
import org.freedesktop.dbus.interfaces.Properties
import org.freedesktop.dbus.types.UInt16
import org.freedesktop.dbus.types.UInt32
import org.freedesktop.dbus.types.UInt64
import org.freedesktop.dbus.types.Variant
import org.gnome.Mutter.DisplayConfig.Struct8
import org.gnome.Mutter.DisplayConfig.Struct9
import org.gnome.Mutter.IDisplayConfig
import java.net.Inet4Address
import java.net.NetworkInterface
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

fun dbusConnect(system: Boolean = false): DBusConnection = DBusConnection.getConnection(if (system) SYSTEM else SESSION)

fun dbusConnectAddr(
    addr: String,
    registerSelf: Boolean = true,
    shared: Boolean = true,
    timeout: Int = AbstractConnection.TCP_CONNECT_TIMEOUT
): DBusConnection = DBusConnection.getConnection(addr, registerSelf, shared, timeout)

fun dbusDefaultObjPath(busName: String) = "/" + busName.replace('.', '/')

inline fun <reified T: DBusInterface> DBusConnection.getRO(
    busName: String,
    objPath: String = dbusDefaultObjPath(busName)
): T = getRemoteObject(busName, objPath, T::class.java)

fun dbusMutterIntro() = dbusConnect().getRO<Introspectable>("org.gnome.Mutter.DisplayConfig").Introspect()

fun dbusScreenSaver() = dbusConnect().getRO<IScreenSaver>("org.freedesktop.ScreenSaver")

fun dbusNetworkManagerProperties() = dbusConnect(system = true).getRO<Properties>("org.freedesktop.NetworkManager")

fun dbusNetworkManagerWifi(enable: Boolean) {
    dbusNetworkManagerProperties().Set("org.freedesktop.NetworkManager", "WirelessEnabled", enable)
}


suspend fun <R> AbstractConnection.dbusCall(obj: DBusInterface, method: String, vararg params: Any): R =
    withContext(IO) {
        suspendCoroutine { cont ->
            val handler = object : CallbackHandler<R> {
                override fun handle(r: R) = cont.resume(r)
                override fun handleError(e: DBusExecutionException) = cont.resumeWithException(e)
            }
            callWithCallback(obj, method, handler, *params)
        }
    }

fun dbusMutterGetResources(): Any {
    val config = dbusConnect().getRO<IDisplayConfig>("org.gnome.Mutter.DisplayConfig")
    return config.GetResources()
}

fun dbusMutterGetResourcesByHand(): Any { // FIXME: does not work - rethink dynamic calls (without code generation)
    val connect = dbusConnect()
    val interf = connect.getRO<DBusInterface>("org.gnome.Mutter.DisplayConfig")
    return runBlocking { connect.dbusCall<Any>(interf, "GetResources") }
}

fun dbusApplyMyBothMonitorsConfig() {
    val config = dbusConnect().getRO<IDisplayConfig>("org.gnome.Mutter.DisplayConfig")
    val configurationSerial = config.GetResources().a
    config.ApplyMonitorsConfig(
        configurationSerial, // configuration serial
        UInt32(2), // configuration method (2:persistent)
        listOf(
            // logical monitors
            Struct8(
                0, 0, 1.0, UInt32(0), true, listOf(
                    Struct9(
                        "DP-2", "2560x1440@59.950550079345703", mapOf(
                            "underscanning" to Variant(false)
                        )
                    )
                )
            ),
            Struct8(
                267, 1440, 1.0, UInt32(0), false, listOf(
                    Struct9(
                        "eDP-1", "1920x1080@59.999324798583984", mapOf(
                            "underscanning" to Variant(false)
                        )
                    )
                )
            ),
        ),
        mapOf()
    )
}


public fun Any?.toKotlinStruct(): String = when {
    this === null -> "null"
    this is String -> "\"$this\""
    this is List<*> -> "listOf(${joinToString { it.toKotlinStruct() }})"

    // FIXME: I assume keys are strings (rethink)
    this is Map<*, *> -> {
        val entriesStr = entries.joinToString { "\"${it.key}\" to ${it.value.toKotlinStruct()}" }
        "mapOf($entriesStr)"
    }
    (this is Tuple) or (this is Struct) -> {
        val propsStr = getPublicPropsVals().joinToString { it.toKotlinStruct() }
        "${this::class.simpleName}($propsStr)"
    }
    (this is UInt16) -> "UInt16(${this.toInt()})"
    (this is UInt32) -> "UInt32(${this.toLong()})"
    (this is UInt64) -> "UInt64(${this.toLong()})"
    this is Variant<*> -> "Variant(${value.toKotlinStruct()})"
    this is ByteArray -> "ByteArrayFixme(\"$this\")"
//    else -> "${this::class.simpleName}($this)" // for debugging
    else -> "$this"
}

@OptIn(ExperimentalStdlibApi::class)
fun <T : Any> T.getPublicPropsVals(): List<Any?> {
    val props = (this::class as KClass<T>).declaredMemberProperties
    return props
        .filter { it.visibility == KVisibility.PUBLIC }
        .map { it(this) }
}

fun dbusDaemonMain(vararg args: String) = DBusDaemon.main(args)

//fun <T> Stream<T>.collectToList(): List<T> = collect(Collectors.toList())
//
//fun getInet4AddressesNewNonAndroid(): List<String> = NetworkInterface.networkInterfaces().collectToList()
//    .map { it.inetAddresses().collectToList().filterIsInstance<Inet4Address>()
//}.flatten().map { it.hostAddress }
//
fun getInet4Addresses(): List<String> = NetworkInterface.getNetworkInterfaces().toList()
    .map { it.inetAddresses.toList().filterIsInstance<Inet4Address>()
    }.flatten().map { it.hostAddress }

