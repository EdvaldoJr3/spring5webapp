package guru.springframework.spring5webapp.BootStrapData;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository autorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository autorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.autorRepository = autorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("IT Publishing");
        publisher.setCity("Rio de Janeiro");
        publisher.setState("RJ");

        publisherRepository.save(publisher);
        System.out.println("Publisher Count: " + publisherRepository.count());

        Author edval = new Author("Edvaldo", "Sales");
        Book aaaa = new Book("Domain my Mind", "123456");
        edval.getBooks().add(aaaa);
        aaaa.getAuthors().add(edval);

        aaaa.setPublisher(publisher);
        publisher.getBooks().add(aaaa);

        autorRepository.save(edval);
        bookRepository.save(aaaa);
        publisherRepository.save(publisher);

        Author rod = new Author("Red", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "234567");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        autorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + publisher.getBooks().size());

    }
}
