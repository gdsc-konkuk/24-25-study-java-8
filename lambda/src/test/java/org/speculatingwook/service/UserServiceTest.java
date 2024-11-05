package org.speculatingwook.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.speculatingwook.user.User;
import org.speculatingwook.user.UserService;

import java.util.*;

/**
 * UserService의 모든 테스트를 통과해보자.
 * - 테스트 코드가 성공적으로 동작 가능하도록 user 디렉토리의 코드를 작성해보자.
 */
public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        userService.addUser(new User("Alice", 25, "HR", 50000));
        userService.addUser(new User("Bob", 30, "IT", 60000));
        userService.addUser(new User("Charlie", 35, "Finance", 70000));
        userService.addUser(new User("David", 40, "IT", 80000));
        userService.addUser(new User("Eve", 28, "HR", 55000));
    }


    // 1
    @Test
    public void testGetAllUserNames() {
        List<String> names = userService.getAllUserNames();
        assertEquals(5, names.size());
        assertTrue(names.containsAll(Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve")));
    }


    // 2
    @Test
    public void testGetUsersSortedByAge() {
        List<User> sortedUsers = userService.getUsersSortedByAge();
        assertEquals(5, sortedUsers.size());
        assertEquals("Alice", sortedUsers.get(0).getName());
        assertEquals("David", sortedUsers.get(4).getName());
    }


    // 3
    @Test
    public void testGetUsersOver30() {
        List<User> usersOver30 = userService.getUsersOver30();
        assertEquals(3, usersOver30.size());
        assertTrue(usersOver30.stream().allMatch(user -> user.getAge() >= 30));
    }


    // 4
    @Test
    public void testGroupUsersByDepartment() {
        Map<String, List<User>> groupedUsers = userService.groupUsersByDepartment();
        assertEquals(3, groupedUsers.size());
        assertEquals(2, groupedUsers.get("HR").size());
        assertEquals(2, groupedUsers.get("IT").size());
        assertEquals(1, groupedUsers.get("Finance").size());
    }

    // 5
    @Test
    public void testGetTotalAge() {
        int totalAge = userService.getTotalAge();
        assertEquals(158, totalAge);
    }


    // 6
    @Test
    public void testGetAverageSalary() {
        double avgSalary = userService.getAverageSalary();
        assertEquals(63000, avgSalary, 0.01);
    }


    // 7
    @Test
    public void testGetUsersInAgeRange() {
        List<User> users = userService.getUsersInAgeRange(25, 35);
        assertEquals(4, users.size());
        assertTrue(users.stream().allMatch(user -> user.getAge() >= 25 && user.getAge() <= 35));
    }

    // 8
    @Test
    public void testFindUserByName() {
        Optional<User> user = userService.findUserByName("Charlie");
        assertTrue(user.isPresent());
        assertEquals(35, user.get().getAge());
    }


    // 9
    @Test
    public void testAreAllUsersAboveAge() {
        assertTrue(userService.areAllUsersAboveAge(20));
        assertFalse(userService.areAllUsersAboveAge(30));
    }


    // 10
    @Test
    public void testFindUser() {
        Optional<User> user = userService.findUser(u -> u.getDepartment().equals("Finance"));
        assertTrue(user.isPresent());
        assertEquals("Charlie", user.get().getName());
    }

    // 11
    @Test
    public void testGetOldestUserByDepartment() {
        Map<String, User> oldestUsers = userService.getOldestUserByDepartment();
        assertEquals(3, oldestUsers.size());
        assertEquals("Eve", oldestUsers.get("HR").getName());
        assertEquals("David", oldestUsers.get("IT").getName());
        assertEquals("Charlie", oldestUsers.get("Finance").getName());
    }

    // 12
    @Test
    public void testGetUserWithLongestName() {
        Optional<User> user = userService.getUserWithLongestName();
        assertTrue(user.isPresent());
        assertEquals("Charlie", user.get().getName());
    }

    // 13
    @Test
    public void testGetUpperCaseNamesOfUsersAboveAge() {
        List<String> names = userService.getUpperCaseNamesOfUsersAboveAge(35);
        assertEquals(1, names.size());
        assertTrue(names.contains("DAVID"));
    }

    // 14
    @Test
    public void testMapUsers() {
        List<String> names = userService.mapUsers(User::getName);
        assertEquals(Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve"), names);
    }


    // 15
    @Test
    public void testGetAllUserNamesToString() {
        String allNames = userService.getAllUserNamesToString();
        assertEquals("Alice, Bob, Charlie, David, Eve", allNames);
    }


    // 16 - 1
    @Test
    public void testGetAverageAgeByDepartment() {
        Map<String, Double> avgAges = userService.getAverageAgeByDepartment();
        assertEquals(3, avgAges.size());
        assertEquals(26.5, avgAges.get("HR"), 0.01);
        assertEquals(35.0, avgAges.get("IT"), 0.01);
        assertEquals(35.0, avgAges.get("Finance"), 0.01);
    }



    // 16 - 2
    @Test
    public void testGetDepartmentsSortedByAverageAge() {
        List<Map.Entry<String, Double>> sortedDepts = userService.getDepartmentsSortedByAverageAge();
        assertEquals(3, sortedDepts.size());
        assertEquals("Finance", sortedDepts.get(0).getKey());
        assertEquals("IT", sortedDepts.get(1).getKey());
        assertEquals("HR", sortedDepts.get(2).getKey());
    }


    // 17
    @Test
    public void testFilterUsersByAge() {
        List<User> users = userService.filterUsers_1(user -> user.getAge() > 30);
        assertEquals(2, users.size());
        assertTrue(users.stream().allMatch(user -> user.getAge() > 30));
    }


    // 18
    @Test
    public void testFilterUsersByName() {
        List<User> users = userService.filterUsers_2(user -> user.getName().startsWith("A"));
        assertEquals(1, users.size());
        assertEquals("Alice", users.get(0).getName());
    }


    // 19
    @Test
    public void testProcessUsers() {
        final int[] count = {0};
        userService.processUsers(user -> {
            if (user.getAge() < 35) {
                count[0]++;
            }
        });
        assertEquals(3, count[0]);
    }


    // 20
    @Test
    public void testSortUsers() {
        userService.sortUsers(Comparator.comparing(User::getAge).reversed());
        List<User> sortedUsers = userService.getUsers();
        assertEquals(40, sortedUsers.get(0).getAge());
        assertEquals(25, sortedUsers.get(sortedUsers.size() - 1).getAge());
    }


    // 21
    @Test
    public void testGetAverageAge() {
        double averageAge = userService.getAverageAge();
        assertEquals(31.6, averageAge, 0.1);
    }
}
