package hessian;

import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianFactory;
import com.mdc.hessian.Book;
import com.mdc.hessian.IBookService;

public class HessianTest {
	public static void main(String[] args) {
		try {
			String url = "http://127.0.0.1:8080/mydubbo/book";
			HessianProxyFactory factory = new HessianProxyFactory();
			IBookService bookService = (IBookService) factory.create(IBookService.class, url);
			List<Book> list = bookService.getList();
			for (Book book : list) {
				System.out.println(book.getName() + "--" + book.getPrice());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
