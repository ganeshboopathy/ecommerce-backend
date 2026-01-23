package com.order.order_service_api.service.impl;

import com.order.order_service_api.dto.member.MemberCreateRequest;
import com.order.order_service_api.dto.member.MemberResponse;
import com.order.order_service_api.dto.member.MemberUpdateRequest;
import com.order.order_service_api.entity.Member;
import com.order.order_service_api.repository.MemberRepository;
import com.order.order_service_api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse createMember(MemberCreateRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .build();
        member = memberRepository.save(member);
        return mapToResponse(member);
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        return mapToResponse(member);
    }

    @Override
    public MemberResponse updateMember(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
        
        if (request.getName() != null) {
            member.setName(request.getName());
        }
        if (request.getEmail() != null) {
            member.setEmail(request.getEmail());
        }
        if (request.getAge() != null) {
            member.setAge(request.getAge());
        }
        
        member = memberRepository.save(member);
        return mapToResponse(member);
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    private MemberResponse mapToResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
