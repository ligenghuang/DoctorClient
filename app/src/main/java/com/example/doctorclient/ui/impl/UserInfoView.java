package com.example.doctorclient.ui.impl;

import com.example.doctorclient.event.DepartidDto;
import com.example.doctorclient.event.DoctorInfoDto;
import com.example.doctorclient.event.GeneralDto;
import com.example.doctorclient.event.HospitalListDto;
import com.example.doctorclient.event.UserInfoDto;
import com.lgh.huanglib.util.base.BaseView;

/**
* 个人信息
* @author lgh
* created at 2019/5/16 0016 10:54
*/
public interface UserInfoView extends BaseView {

    void getDoctorsInfo();

    void getDoctorsInfoSuccessful(DoctorInfoDto userInfoDto);

    void getFindDepartid();

    void getFindDepartidSuccessful(DepartidDto departidDto);

    void sevaDoctorsInfo();

    void sevaDoctorsInfoSuccessful(GeneralDto generalDto);

    void getHospitalName();
    void getHospitalNameSuccessful(HospitalListDto hospitalListDto);

}
