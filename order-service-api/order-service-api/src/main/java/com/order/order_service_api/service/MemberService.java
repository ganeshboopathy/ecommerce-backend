package com.order.order_service_api.service;

import com.order.order_service_api.dto.member.MemberCreateRequest;
import com.order.order_service_api.dto.member.MemberResponse;
import com.order.order_service_api.dto.member.MemberUpdateRequest;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(MemberCreateRequest request);
    List<MemberResponse> getAllMembers();
    MemberResponse getMemberById(Long id);
    MemberResponse updateMember(Long id, MemberUpdateRequest request);
    void deleteMember(Long id);
}
