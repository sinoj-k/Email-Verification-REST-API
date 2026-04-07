package com.EmailVerification.Repo;

import com.EmailVerification.Model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<TokenModel, Long> {

    Optional<TokenModel> findByToken(String token);
}
