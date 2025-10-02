package com.app.e_commerce.repository;

import com.app.e_commerce.entity.UserPersonalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalRepo extends JpaRepository<UserPersonalDetail,Long>{

}
