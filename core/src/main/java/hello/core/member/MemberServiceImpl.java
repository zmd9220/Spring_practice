package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 가입을 하고 조회를 하기 위해 멤버리포지토리가 필요
    // 구현체 없는 단순 호출은 null point exception 호출 (에러)
    // 구현객체(MemoryMemberRepository)를 선택해줘야함
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // AppConfig로 사용하기 위해 구현체 연결을 없앰 -> DIP를 지키게 됨
    private final MemberRepository memberRepository;

    // 생성자를 통해 멤버리토지토리에 들어갈 구현체를 선택하게 됨
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
