package practice7.constants;

public enum Xml {
    DEVICES("devices"),
    DEVICE("device"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    TYPE("type"),
    CRITICAL("critical"),
    PERIPHERAL("peripheral"),
    COOLER("cooler"),
    POWER("power"),
    GROUP("group"),
    PORTS("ports"),
    PORT("port"),

    ATTR_CURRENCY("currency"),
    ATTR_VALUE("value");

    private String value;

    public String value() {
        return value;
    }

    Xml(String value) {
        this.value = value.intern();
    }
}
