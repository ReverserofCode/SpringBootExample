package org.zerock.ex1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.ex1.entity.Memo;
import org.zerock.ex1.repository.MemoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class Ex1ApplicationTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample....").build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("======================================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("UpdateText").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void delete() {
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("------------------------------------------------");
        System.out.println("Total Pages: "+result.getTotalPages());
        System.out.println("Total Count: "+result.getTotalElements());
        System.out.println("Page Number: "+result.getNumber());
        System.out.println("Page Size: "+result.getSize());
        System.out.println("has next page: "+result.hasNext());
        System.out.println("first page: "+result.isFirst());
        System.out.println("-------------------------------------------");
         for(Memo memo : result.getContent()){
            System.out.println(memo);
        }
    }

    @Test
    public void testSort(){
        Sort sort1 = Sort.by("mno").descending();
    }

    @Test
    public void testQueryMethods(){
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for(Memo memo : list) System.out.println(memo);
    }


}
