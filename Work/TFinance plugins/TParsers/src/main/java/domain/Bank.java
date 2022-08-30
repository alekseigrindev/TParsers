package domain;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Bank {

    private String code;

    private String name;

    private String address;

    private String provinceName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceName() {
        return provinceName;
    }

    @JsonSetter("province_name")
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
