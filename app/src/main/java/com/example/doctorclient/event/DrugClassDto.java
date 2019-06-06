package com.example.doctorclient.event;

import java.util.ArrayList;
import java.util.List;

public class DrugClassDto {
    private String name;
    private boolean isClick;
    private List<DrugListDto.DataBean> dataBean;

    public List<DrugListDto.DataBean> getDataBean() {
        if (dataBean == null) {
            return new ArrayList<>();
        }
        return dataBean;
    }

    public void setDataBean(List<DrugListDto.DataBean> dataBean) {
        this.dataBean = dataBean;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
