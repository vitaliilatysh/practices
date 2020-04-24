package practice7.entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Power", propOrder = {"content"})
public class Power {
    @XmlElement
    private Integer content;

    @XmlAttribute
    protected String value;

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Power{" +
                "content=" + content +
                ", value='" + value + '\'' +
                '}';
    }
}
