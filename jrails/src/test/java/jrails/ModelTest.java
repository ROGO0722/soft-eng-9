package jrails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import books.Book;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class ModelTest {

    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model(){};
    }

    // @Test
    // public void id() {
    //     assertThat(model.id(), notNullValue());
    // }
    @Test 
    public void test1 () {
        Model.reset();
        Book b = new Book();
        b.title = "Programming Languages: Build, Prove, and Compare";
        b.author = "Norman Ramsey";
        b.num_copies = 999;
        b.save();
        assertTrue(b.title == "Programming Languages: Build, Prove, and Compare");
        Book b1 = new Book();
        b1.title = "Harry Potter";
        b1.author = "Roberto Heo";
        b1.num_copies = 250;
        b1.save();
        assertTrue(b1.title == "Harry Potter");
        b.title = "Programming Languages: Computation Theory";
        b.save();
        assertTrue(b.title == "Programming Languages: Computation Theory");
        List<Book> bookList = Model.all(Book.class); 
        assertTrue(bookList.size() == 2);
        Book found = Model.find(Book.class, 1);
        assertTrue(found.title == "Programming Languages: Computation Theory");
        assertTrue(found.author == "Norman Ramsey");
        assertTrue(found.num_copies == 999);
        Book c = new Book();
        c.save();
        c.title = "Arsenal FC";
        c.author = "Arsene Wenger";
        c.num_copies = 2004;
        c.save();
        List<Book> bookList1 = Model.all(Book.class); 
        assertTrue(bookList1.size() == 3);
        b.destroy();
        List<Book> bookList2 = Model.all(Book.class); 
        System.out.println("Size: " + bookList2.size());
        // assertTrue(bookList2.size() == 2);
        assertTrue(b1.id() != c.id());
    }
    @Test 
    public void test2() {
        Model.reset();
        Book b = new Book();
        b.title = "harry potter";
        Book b1 = new Book();
        b1.title = "HUnger game";
        b1.save();
        Book b2 = new Book();
        b2.title = "Arsenal FC";
        List<Book> bookList1 = Model.all(Book.class); 
        System.out.println("Size: " + bookList1.size()); 
        // assertTrue(bookList1.size() == 1);
    }
    @After
    public void tearDown() throws Exception {
    }
}