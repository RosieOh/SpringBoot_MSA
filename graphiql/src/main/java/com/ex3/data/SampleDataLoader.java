package com.ex3.data;

import com.ex3.entity.Address;
import com.ex3.entity.Person;
import com.ex3.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements CommandLineRunner {
    private final PersonRepository personRepository;

    public SampleDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 임시 데이터 1
        Person person = new Person("ktlove",
                "kim",
                "010-1234-1234",
                "kktlove@gmail.com",
                new Address("SejongDaero18","Sejong-City","Hill-State","81340"));
        personRepository.save(person);

        // 임시 데이터 2
        person = new Person("Chunjae",
                "Oh",
                "010-1004-7979",
                "kimchunjae@naver.com",
                new Address("Donggu123","IllSan-City","-","79431"));
        personRepository.save(person);

        // 임시 데이터 3
        person = new Person("Sun",
                "Han",
                "010-2848-1212",
                "hansun@chunjae.com",
                new Address("NambuSoonHwanLoad8","Seoul-City","Prize","12345"));
        personRepository.save(person);
    }
}
