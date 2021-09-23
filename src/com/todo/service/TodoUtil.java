package com.todo.service;

import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n=====항목추가=====");
		System.out.print("일정을 입력해주세요: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.print("상세설명을 입력해주세요: ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.println("\n====일정삭제====");
		System.out.print("삭제할 일정을 입력하세요: ");
				
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		

		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n====일정 수정====");
		System.out.print("수정할 일정의 제목을 입력해주세요: ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 일정입니다. ");
			return;
		}

		System.out.print("일정의 새로운 제목을 입력해주세요: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 존재하는 이름입니다. ");
			return;
		}
		
		System.out.print("새로운 상세 설명을 입력해주세요: ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("일정이 수정되었습니다. ");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveSring());
			}

			w.close();
			
			System.out.println("저장 완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			while((oneline = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String detail = st.nextToken();
				String date = st.nextToken();
				
				TodoItem item = new TodoItem(title, detail);
				
				System.out.println(l.toString());
			}
			System.out.println("로딩완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
