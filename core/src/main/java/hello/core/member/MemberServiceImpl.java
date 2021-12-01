package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 가입을 하고 조회를 하기 위해 멤버리포지토리가 필요
    // 구현체 없는 단순 호출은 null point exception 호출 (에러)
    // 구현객체(MemoryMemberRepository)를 선택해줘야함
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
