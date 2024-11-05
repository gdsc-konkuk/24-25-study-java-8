package org.speculatingwook.user;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    /**
     * 1. 모든 사용자의 이름 목록을 반환한다.
     * @return 사용자 이름 목록
     */
    public List<String> getAllUserNames() {
        return null;
    }

    /**
     * 2. 나이순으로 사용자 목록을 정렬한다.
     * @return 나이순으로 정렬된 사용자 목록
     */
    public List<User> getUsersSortedByAge() {
        return null;
    }

    /**
     * 3. 나이가 30 이상인 사용자 목록을 반환한다.
     * @return 나이가 30 이상인 사용자 목록
     */
    public List<User> getUsersOver30() {
        return null;
    }

    /**
     * 4. 부서별로 사용자 목록을 그룹화한다.
     * @return 부서별 사용자 목록
     */
    public Map<String, List<User>> groupUsersByDepartment() {
        return null;
    }

    /**
     * 5. 모든 사용자의 나이 합을 계산한다.
     * @return 사용자 나이 합
     */
    public int getTotalAge() {
        return 0;
    }

    /**
     * 6. 모든 사용자의 평균 급여를 계산한다.
     * @return 평균 급여
     */
    public double getAverageSalary() {
        return 0;
    }

    /**
     * 7. 나이의 범위 내에 있는 사용자 목록을 반환한다.
     * @param minAge 최소 나이
     * @param maxAge 최대 나이
     * @return 나이 범위 내의 사용자 목록
     */
    public List<User> getUsersInAgeRange(int minAge, int maxAge) {
        return null;
    }

    /**
     * 8. 특정 이름을 가진 사용자를 검색한다.
     * @param name 사용자 이름
     * @return 이름이 일치하는 사용자
     */
    public Optional<User> findUserByName(String name) {
        return null;
    }

    /**
     * 9. 모든 사용자가 특정 나이 이상인지 확인한다.
     * @param age 기준 나이
     * @return 모든 사용자가 해당 나이 이상이면 true, 아니면 false
     */
    public boolean areAllUsersAboveAge(int age) {
        return false;
    }

    /**
     * 10. 주어진 조건에 일치하는 사용자를 검색한다.
     * @param predicate 검색 조건
     * @return 조건에 맞는 사용자
     */
    public Optional<User> findUser(Predicate<User> predicate) {
        return null;
    }

    /**
     * 11. 부서별로 가장 나이가 많은 사용자를 찾는다.
     * @return 부서별 가장 나이 많은 사용자
     */
    public Map<String, User> getOldestUserByDepartment() {
        return null;
    }

    /**
     * 12. 이름의 길이가 가장 긴 사용자를 찾는다.
     * @return 이름이 가장 긴 사용자
     */
    public Optional<User> getUserWithLongestName() {
        return null;
    }

    /**
     * 13. 특정 나이 이상인 사용자의 이름을 대문자로 변환한다.
     * @param age 기준 나이
     * @return 대문자로 변환된 사용자 이름 목록
     */
    public List<String> getUpperCaseNamesOfUsersAboveAge(int age) {
        return null;
    }

    /**
     * 14. 사용자 목록을 주어진 매퍼 함수를 통해 변환한다.
     * @param mapper 변환 함수
     * @param <R> 변환 후의 타입
     * @return 변환된 사용자 목록
     */
    public <R> List<R> mapUsers(Function<User, R> mapper) {
        return null;
    }

    /**
     * 15. 모든 사용자 이름을 연결하여 하나의 문자열로 만든다.
     * @return 연결된 사용자 이름 문자열
     */
    public String getAllUserNamesToString() {
        return null;
    }

    /**
     * 16-1. 부서별 평균 나이를 계산한다.
     * @return 부서별 평균 나이
     */
    public Map<String, Double> getAverageAgeByDepartment() {
        return null;
    }

    /**
     * 16-2. 부서별 평균 나이를 기준으로 부서를 내림차순으로 정렬한다.
     * @return 정렬된 부서 목록과 평균 나이
     */
    public List<Map.Entry<String, Double>> getDepartmentsSortedByAverageAge() {
        return null;
    }

    /**
     * 17. 주어진 조건에 맞는 사용자 목록을 필터링한다.
     * @param predicate 필터 조건
     * @return 필터링된 사용자 목록
     */
    public List<User> filterUsers_1(Predicate<User> predicate) {
        return null;
    }

    /**
     * 18. 주어진 조건에 맞는 사용자 목록을 필터링한다.
     * @param predicate 필터 조건
     * @return 필터링된 사용자 목록
     */
    public List<User> filterUsers_2(Predicate<User> predicate) {
        return null;
    }

    /**
     * 19. 주어진 작업을 각 사용자에게 수행한다.
     * @param consumer 작업
     */
    public void processUsers(Consumer<User> consumer) {
    }

    /**
     * 20. 사용자 목록을 주어진 비교 기준에 따라 정렬한다.
     * @param comparator 비교 기준
     */
    public void sortUsers(Comparator<User> comparator) {
    }

    /**
     * 21. 모든 사용자의 평균 나이를 계산한다.
     * @return 평균 나이
     */
    public double getAverageAge() {
        return 0;
    }

    /**
     * 모든 사용자 목록을 반환한다.
     * @return 사용자 목록
     */
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
