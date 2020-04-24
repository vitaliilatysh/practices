package practice7.util;

import practice7.entities.Device;
import practice7.entities.Devices;

import java.util.Comparator;

public class Sorter {

    private static final Comparator<Device> sortDevicesByName = Comparator.comparing(Device::getName);
    private static final Comparator<Device> sortDevicesByPortSizes = Comparator.comparingInt(o -> o.getType().getPorts().size());
    private static final Comparator<Device> sortDevicesByPrice = Comparator.comparing(o -> o.getPrice().getContent());

    public static void sortDevicesByName(Devices devices) {
        devices.getDevices().sort(sortDevicesByName);
    }

    public static void sortDevicesByPortSizes(Devices devices) {
        devices.getDevices().sort(sortDevicesByPortSizes);
    }

    public static void sortDevicesByPrice(Devices devices) {
        devices.getDevices().sort(sortDevicesByPrice);
    }

}
