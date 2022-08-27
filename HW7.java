import java.util.Arrays;
import java.util.Date;

class User{
    private final String firstName, secondName;

    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return  firstName + " " + secondName ;
    }
}


class Book{
    private final String nameOfBook;
    private final String authorOfBook;
    private final double price;
    private Date dateOfIssue;
    private Date returnDate;

    public Book(String nameOfBook, String authorOfBook, double price) {
        this.nameOfBook = nameOfBook;
        this.authorOfBook = authorOfBook;
        this.price = price;
    }


    public String getNameOfBook() {
        return nameOfBook;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public double getPrice() {
        return price;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return  " Название книги: '" + nameOfBook + '\'' +
                ", Автор : '" + authorOfBook + '\'' +
                ", стоимость : " + price
                ;
    }

}

interface UserLibrary {
    User getUser();
}

interface ReaderBook extends UserLibrary{
    Book[] takeBooks();
    Book[] returnBooks();
}

interface Librarian extends UserLibrary{
    Book[] orderBooks();
}

interface Supplier extends UserLibrary{
    Book[] vendorBooks();
}

interface Admin extends UserLibrary{
    Book[] issueBook();
    void notifyAboutDelay(User user, Book book);

}


class UserReader implements ReaderBook{

    private final User user;
    private final Book[] takeBooks;
    private final Book[] returnBooks;


    public UserReader(User user, Book[] takebooks, Book[] returnedBooks) {
        this.user = user;
        this.takeBooks = takebooks;
        this.returnBooks = returnedBooks;
    }

    @Override
    public User getUser() {
        return this.user;
    }


    @Override
    public Book[] takeBooks() {
        return this.takeBooks;
    }

    @Override
    public Book[] returnBooks() {
        return this.returnBooks;
    }

    @Override
    public String toString() {
        return
                "Читатель :" + user + '\n' +
                "Полученные книги  : " + Arrays.toString(takeBooks) + '\n' +
                "Вернул книги : " + Arrays.toString(returnBooks) + '\n'
                ;
    }
}

class UserSupplier implements Supplier{

    private final User user;
    private final Book[] vendirbooks;

    public UserSupplier(User user, Book[] vendirbooks) {
        this.user = user;
        this.vendirbooks = vendirbooks;
    }

    @Override
    public Book[] vendorBooks() {
        return vendirbooks;
    }


    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return
                "Поставщик :" + this.user + '\n' +
                "Доставил книги  : " + Arrays.toString(this.vendirbooks) + '\n'
                ;
    }

}

class UserLibrarian implements Librarian {
    private final User user;
    private final Book[] orderBooks;

    public UserLibrarian(User user, Book[] orderBooks) {
        this.user = user;
        this.orderBooks = orderBooks;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Book[] orderBooks() {
        return orderBooks;
    }

    @Override
    public String toString() {
        return
                "Библиотекарь :" + this.user + '\n' +
                "Заказал книги  : " + Arrays.toString(this.orderBooks) + '\n'
                ;
    }
}

class UserAdmin implements Admin{
    private final User user;
    private final Book[] issueBook;

    public UserAdmin(User user, Book[] issueBook) {
        this.user = user;
        this.issueBook = issueBook;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public Book[] issueBook() {
        return new Book[0];
    }

    @Override
    public void notifyAboutDelay(User user, Book book) {
        System.out.println("Администратор сообщил : " + user );
        System.out.println("Вернуть книгу : " + book);
    }

    @Override
    public String toString() {
        return
                "Администратор :" + this.user + '\n' +
                "Выдал книги  : " + Arrays.toString(this.issueBook) + '\n'
                ;
    }
}


public class HW7 {
    public static void main(String[] args) {

        UserReader userReader = new UserReader(
                new User("Филин", "Константин"),
                new Book[]{
                        new Book("Война и Мир", "Л.Н.Толстой", 100.0),
                        new Book("Анна Каренина", "Л.Н.Толстой", 200.0)
                },
                new Book[]{
                        new Book("Как Лис Ежа перехитрил", "Бианки, Виталий Валентинович", 120.0)
                }
        );

        System.out.println(userReader);

        UserLibrary userLibrary = new UserLibrarian(
                new User("Василий", "Иванов"),
                new Book[]{
                        new Book("Как Лис Ежа перехитрил", "Бианки, Виталий Валентинович", 120.0),
                        new Book("Учимся хорошим манерам", "Бомон, Эмили", 240.0),
                        new Book("Что случилось в нашем классе?", "Дружинина, Марина", 240.0)
                }
        );

        System.out.println(userLibrary);

        UserSupplier userSupplier = new UserSupplier(
                new User("Григорий", "Смирнов"),
                new Book[]{
                        new Book("Воскресение", "Л.Н.Толстой", 160.0)
                }
        );

        System.out.println(userSupplier);


        UserAdmin userAdmin = new UserAdmin(
                new User("Светлана", "Полисменова"),
                new Book[]{
                        new Book("Учимся хорошим манерам", "Бомон, Эмили", 240.0)
                }
        );

        System.out.println(userAdmin);
        userAdmin.notifyAboutDelay(
                new User("Филин", "Константин"),
                new Book("Анна Каренина", "Л.Н.Толстой", 200.0)
        );
    }
}
