package com.todo.service;

import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n=====항목추가=====");
		System.out.print("카테고리를 입력해주세요: ");
		category = sc.next();
		
		System.out.print("일정을 입력해주세요: ");		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.print("상세설명을 입력해주세요: ");
		desc = sc.next();
		
		System.out.print("마감일을 입력해주세요: ");
		due_date= sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		System.out.println("\n====일정삭제====");
		System.out.print("삭제할 일정의 번호를 입력하세요: ");
				
		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt();
		int count=0;

		for (TodoItem item : l.getList()) {
			count++;
			if (index == count) {
				System.out.println(count + ". [" + item.getCategory() + "] " + " Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc() + " Due Date: "+ item.getDue_date());
				System.out.print("위 항목을 삭제하시겠습니까? (y/n) >> ");
				if ("y".equals(sc.next())) {
					l.deleteItem(item);
					break;					
				}
				else {
					System.out.println("삭제되지 않았습니다.");
					break;
				}

			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n====일정 수정====");
		System.out.print("수정할 일정의 번호를 입력해주세요: ");
		int index = sc.nextInt();
		int count = 0;
		for (TodoItem item : l.getList()) {
			count++;
			if (index == count) {
				l.deleteItem(item);
				System.out.print("일정의 새로운 제목을 입력해주세요: ");
				String new_title = sc.next().trim();
				
				System.out.print("일정의 새로운 카테고리를 입력해주세요: ");
				String new_category = sc.next().trim();
				
				System.out.print("일정의 새로운 d-day을 입력해주세요: ");
				String new_due_date = sc.next().trim();
				
				System.out.print("새로운 상세 설명을 입력해주세요: ");
				String new_description = sc.next().trim();
				
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);				
				
				System.out.println("일정이 수정되었습니다. ");
			}
		}


	}

	public static void listAll(TodoList l) {
		System.out.println(l.size() + "개의 항목이 있습니다.");
		int count = 0;
		for (TodoItem item : l.getList()) {
			count++;
			System.out.println(count + ". [" + item.getCategory() + "] " + "   Item Title: " + item.getTitle() + "   Item Description:  " + item.getDesc() + "   Due Date: "+ item.getDue_date() + "   - " + item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
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
			int count = 0;
			while((oneline = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String detail = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				
				TodoItem item = new TodoItem(category, title, detail, due_date, date);
				l.addItem(item);
				count++;
			}
			br.close();
			System.out.println(count+"개의 항목을 읽었습니다!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void findItem(TodoList l, String word) {
		
		int count = 0;
		for (TodoItem item : l.getList()) {
			String txt = item.toSaveString();
			if(txt.contains(word)) {
				count++;
				System.out.println(count + ". [" + item.getCategory() + "] " + "   Item Title: " + item.getTitle() + "   Item Description:  " + item.getDesc() + "   Due Date: "+ item.getDue_date() + "   - " + item.getCurrent_date());				
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}

	public static void findCate(TodoList l, String cate) {
		// TODO Auto-generated method stub
		
		int count = 0;
		for (TodoItem item : l.getList()) {
			String txt = item.toSaveString();
			if(item.getCategory().contains(cate)) {
				count++;
				System.out.println(count + ". [" + item.getCategory() + "] " + "   Item Title: " + item.getTitle() + "   Item Description:  " + item.getDesc() + "   Due Date: "+ item.getDue_date() + "   - " + item.getCurrent_date());				
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}

	public static void listCate(TodoList l) {
		// TODO Auto-generated method stub
		List<String> cateList = new ArrayList<>();
		for (TodoItem item : l.getList()) {
			cateList.add(item.getCategory());			
		}
		HashSet<String> set1 = new HashSet<String>(cateList);
		Iterator iter = set1.iterator();
		boolean next = true;
		while(next) {
			System.out.print(iter.next());
			next = iter.hasNext();
			if(next) {
				System.out.print(" / ");
			}
		}
		System.out.println("\n총 " + set1.size() + "개의 카테고리가 등록되어 있습니다.");
		
	}

}
