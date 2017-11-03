package com.mdc.hessian;

import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.server.HessianServlet;

public class BookServiceImpl extends HessianServlet implements IBookService {

	@Override
	public List<Book> getList() {
		// TODO Auto-generated method stub
		List<Book> list = new ArrayList<Book>();
		Book b1 = new Book();
		b1.setName("<<时间简史>>");
		b1.setPrice("56");
		Book b2 = new Book();
		b2.setName("<<计算机网络>>");
		b2.setPrice("48");
		list.add(b1);
		list.add(b2);
		return list;
	}

}
