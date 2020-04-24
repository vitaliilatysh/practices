package practice7.entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Price", propOrder = {"content"})
public class Price {

    @XmlElement
    private Integer content;

    @XmlAttribute
    protected String currency;

    @Override
    public String toString() {
        return "Price{" +
                "content=" + content +
                ", currency='" + currency + '\'' +
                '}';
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
