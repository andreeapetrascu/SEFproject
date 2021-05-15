package project.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import project.Controllers.WritermainController;
import project.Exceptions.*;
import project.Actions.Book;
import project.Actions.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class BookService {

    private static List<Book> books;
    private static final Path BOOKS_PATH = FileSystemService.getPathToFile(new String[]{"config", "books.json"});

    public BookService() {
    }

    public static void loadBookFromFile() throws IOException {
        if (!Files.exists(BOOKS_PATH, new LinkOption[0])) {
            FileUtils.copyURLToFile(BookService.class.getClassLoader().getResource("books.json"), BOOKS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        books = (List)objectMapper.readValue(BOOKS_PATH.toFile(), new TypeReference<List<Book>>() {
        });
    }

    public static void addBook(String bookname, String imgurl, int quantity, double price, String noPages, int numItems) throws BookAlreadyExists {
        String writeruser= WritermainController.getWriter().getUsername();
        String writername = WritermainController.getWriter().getName();
        checkBookDoesNotAlreadyExist(bookname,writeruser);
        books.add(new Book(writername, bookname, writeruser, imgurl, quantity, price, noPages,numItems));
        persistBooks();
    }


    private static void checkBookDoesNotAlreadyExist(String bookname, String writeruser) throws BookAlreadyExists {

        Iterator var1 = books.iterator();

        Book book;
        do {
            if (!var1.hasNext()) {
                return;
            }

            book = (Book) var1.next();
        } while (!Objects.equals(bookname, book.getBookName()));
        if (Objects.equals(writeruser, book.getWriterUsername()))
            throw new BookAlreadyExists(bookname);
    }


    private static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_PATH.toFile(), books);
        } catch (IOException var1) {
            throw new CouldNotWriteBooksException();
        }
    }
    public static List<Book> getBooks(String writeruser)throws Exception {
        BookService.loadBookFromFile();
        List<Book> prod = new ArrayList<>();
        for (Book pr : books){
            if (Objects.equals(writeruser, pr.getWriterUsername())){
                prod.add(pr);
            }
        }
        return prod;

    }

    public static void updateNumberOfItems (Book pr) throws BookNotInStock{

        for (Book p:books){
            if(p.equals(pr)){
                if (p.getNoOfItems()!=0){
                    p.setNoOfItems(p.getNoOfItems()-1);
                    BookService.persistBooks();
                }
                else throw new BookNotInStock();
            }
        }

    }

    public static void editBook (Book pr, String name, String ImgURL, int quant, int nr, double price, String noPages) {
        for (Book p: books) {
            if (p.equals(pr))
            {
                p.setBookName(name);
                p.setImageUrl(ImgURL);
                p.setQuantity(quant);
                p.setNoOfItems(nr);
                p.setPrice(price);
                p.setnoPages(noPages);

                BookService.persistBooks();
            }
        }
    }

    public static void deleteBook (Book p)
    {

        Iterator<Book> iter = books.iterator();

        while (iter.hasNext()) {
            Book pr = iter.next();

            if (pr.equals(p)) {
                iter.remove();
                BookService.persistBooks();
            }

        }


    }

}
