package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

//    단순 hashmap으로 작성했을 경우 동시성 이슈가 있기 때문에 concurrencthashmap을 해야하지만, 여기는 예제이므로 단순 구현
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
