package org.gnome.mutter;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

import java.util.List;
import java.util.Map;

/**
 * Unfortunally created by hand (dbus-java failed)
 */
public class MyLocalMonitorStruct extends Struct {
    @Position(0)
    private final String connector;
    @Position(1)
    private final String monitorModeId;
    @Position(2)
    private final Map<String, Variant<?>> monitorProperties;

    public MyLocalMonitorStruct(String connector, String monitorModeId, Map<String, Variant<?>> monitorProperties) {
        this.connector = connector;
        this.monitorModeId = monitorModeId;
        this.monitorProperties = monitorProperties;
    }


    public String getConnector() {
        return connector;
    }

    public String getMonitorModeId() {
        return monitorModeId;
    }

    public Map<String, Variant<?>> getMonitorProperties() {
        return monitorProperties;
    }
}
