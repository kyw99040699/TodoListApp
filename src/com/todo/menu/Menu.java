package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<사용 설명서>");
        System.out.println("1. add - 일정 추가");
        System.out.println("2. del - 일정 삭제");
        System.out.println("3. edit - 일정 변경");
        System.out.println("4. ls - 일정 보기");
        System.out.println("5. ls_name_asc - 이름 올림차순 정렬");
        System.out.println("6. ls_name_desc - 이름 내림차순 정렬");
        System.out.println("7. ls_date - 입력 순서대로 정렬");
        System.out.println("8. ls_date_desc - 최신순 정렬");
        System.out.println("9. find - 키워드를 포함하는 모든 항목 출력");
        System.out.println("10. find_cate - 카테고리를 검색");
        System.out.println("11. ls_cate - 카테고리 목록 출력");
        System.out.println("12. exit - 종료");
    }
    
    public static void prompt() {
    	System.out.print("\n입력 >> ");
    }
}
