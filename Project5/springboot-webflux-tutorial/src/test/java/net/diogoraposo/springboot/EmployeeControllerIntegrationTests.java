package net.diogoraposo.springboot;

import net.diogoraposo.springboot.dto.EmployeeDto;
import net.diogoraposo.springboot.repository.EmployeeRepository;
import net.diogoraposo.springboot.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTests {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void before(){
        System.out.println("Benfore Each Test");
        employeeRepository.deleteAll().subscribe();
    }

    public EmployeeDto createEmployee1(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Neil");
        employeeDto.setLastName("Armstrong");
        employeeDto.setEmail("neil@gmail.com");

        return employeeDto;
    }

    public EmployeeDto createEmployee2(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Albert");
        employeeDto.setLastName("Einstein");
        employeeDto.setEmail("albert@gmail.com");

        return employeeDto;
    }

    @Test
    public void testSaveEmployee(){
        EmployeeDto employeeDto = createEmployee1();

        webTestClient.post().uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeDto), EmployeeDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void testGetSingleEmployee(){
        EmployeeDto employeeDto = createEmployee2();

        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();

        webTestClient.get().uri("/api/employees/{id}", Collections.singletonMap("id",savedEmployee.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath(".id").isEqualTo(savedEmployee.getId())
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void testGetAllEmployees(){
        EmployeeDto employeeDto = createEmployee1();
        employeeService.saveEmployee(employeeDto).block();

        EmployeeDto employeeDto2 = createEmployee2();
        employeeService.saveEmployee(employeeDto2).block();


        webTestClient.get().uri("/api/employees")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeDto.class)
                .consumeWith(System.out::println);
    }


    @Test
    public void testUpdateEmployee(){

        EmployeeDto employeeDto = createEmployee1();

        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();

        EmployeeDto updatedEmployee = new EmployeeDto();
        updatedEmployee.setFirstName("Neil-updated");
        updatedEmployee.setLastName("Armstrong-updated");
        updatedEmployee.setEmail("neil-updated@gmail.com");

        webTestClient.put().uri("/api/employees/{id}", Collections.singletonMap("id", savedEmployee.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updatedEmployee), EmployeeDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(updatedEmployee.getFirstName())
                .jsonPath("$.lastName").isEqualTo(updatedEmployee.getLastName())
                .jsonPath("$.email").isEqualTo(updatedEmployee.getEmail());

    }


    @Test
    public void testDeleteEmployee(){
        EmployeeDto employeeDto = createEmployee1();

        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();
        webTestClient.delete().uri("/api/employees/{id}", Collections.singletonMap("id",savedEmployee.getId()))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .consumeWith(System.out::println);
    }
}
