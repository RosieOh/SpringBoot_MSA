package com.member2.service;

import com.member2.domain.Member;
import com.member2.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}
    Member existByEmail(String email);
    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

}