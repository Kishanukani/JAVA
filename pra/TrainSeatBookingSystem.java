import java.util.*;

// Ticket class
class Ticket {
    private int ticketId;
    private String departureDate;
    private boolean isCancelled;

    public Ticket(int ticketId, String departureDate) {
        this.ticketId = ticketId;
        this.departureDate = departureDate;
        this.isCancelled = false;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancelTicket() {
        this.isCancelled = true;
        System.out.println("Ticket " + ticketId + " has been cancelled successfully.");
    }
}

// User class
class User {
    private int userId;
    private List<Ticket> bookedTickets;

    public User(int userId) {
        this.userId = userId;
        this.bookedTickets = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void bookTicket(int ticketId, String departureDate) {
        Ticket ticket = new Ticket(ticketId, departureDate);
        bookedTickets.add(ticket);
        System.out.println("Ticket " + ticketId + " booked for departure date: " + departureDate);
    }

    public void showBookedTickets() {
        System.out.println("Booked Tickets for User " + userId + ":");
        for (Ticket ticket : bookedTickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Departure Date: " + ticket.getDepartureDate() +
                    ", Status: " + (ticket.isCancelled() ? "Cancelled" : "Active"));
        }
    }
}

// Train class
class Train {
    private int trainId;
    private String origin;
    private String destination;
    private String departureTime;
    private int totalSeats;
    private int availableSeats;
    private List<Integer> allocatedSeats; // Track allocated seat numbers

    public Train(int trainId, String origin, String destination, String departureTime, int totalSeats,
            int availableSeats) {
        this.trainId = trainId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.allocatedSeats = new ArrayList<>();
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void allocateSeats(int numSeats, List<Integer> seats) {
        availableSeats -= numSeats;
        allocatedSeats.addAll(seats);
    }

    public void displayTrainInfo() {
        System.out.println("Train ID: " + trainId + ", Origin: " + origin + ", Destination: " + destination +
                ", Departure Time: " + departureTime + ", Available Seats: " + availableSeats);
    }

    public List<Integer> getAllocatedSeats() {
        return allocatedSeats;
    }
}

public class TrainSeatBookingSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Train> trains = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulating users in the system
        User user1 = new User(1);
        user1.bookTicket(101, "2024-09-25");
        user1.bookTicket(102, "2024-10-05");

        User user2 = new User(2);
        user2.bookTicket(201, "2024-11-15");
        user2.bookTicket(202, "2024-12-01");

        // Adding users to the system
        users.add(user1);
        users.add(user2);

        // Simulating trains in the system
        trains.add(new Train(101, "CityA", "CityB", "14:30", 100, 50));
        trains.add(new Train(102, "CityA", "CityC", "09:45", 120, 0)); // Fully booked
        trains.add(new Train(103, "CityB", "CityC", "16:00", 150, 10));
        trains.add(new Train(104, "CityA", "CityB", "08:00", 90, 20));
        trains.add(new Train(105, "CityC", "CityB", "10:15", 80, 5));

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Booking History");
            System.out.println("2. View Trains Sorted by Departure Time");
            System.out.println("3. Search for Trains with Available Seats");
            System.out.println("4. Book Tickets with Dynamic Seat Allocation");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    viewBookingHistory(scanner);
                    break;
                case 2:
                    viewTrainsSortedByTime(scanner);
                    break;
                case 3:
                    searchTrainsWithAvailableSeats(scanner);
                    break;
                case 4:
                    bookWithDynamicSeatAllocation(scanner);
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void viewBookingHistory(Scanner scanner) {
        System.out.println("Enter your Customer ID to view booking history:");
        int customerId = scanner.nextInt();

        User user = findUserById(customerId);
        if (user != null) {
            user.showBookedTickets();
        } else {
            System.out.println("Error: Customer ID not found.");
        }
    }

    private static void viewTrainsSortedByTime(Scanner scanner) {
        System.out.println("Enter Origin City:");
        String origin = scanner.nextLine();

        System.out.println("Enter Destination City:");
        String destination = scanner.nextLine();

        List<Train> availableTrains = filterTrainsByRoute(origin, destination);
        if (availableTrains.isEmpty()) {
            System.out.println("No trains found between " + origin + " and " + destination);
        } else {
            availableTrains.sort(Comparator.comparing(Train::getDepartureTime));
            System.out.println("Trains sorted by Departure Time:");
            for (Train train : availableTrains) {
                train.displayTrainInfo();
            }
        }
    }

    private static void searchTrainsWithAvailableSeats(Scanner scanner) {
        System.out.println("Enter Origin City:");
        String origin = scanner.nextLine();

        System.out.println("Enter Destination City:");
        String destination = scanner.nextLine();

        System.out.println("Enter Number of Tickets to Book:");
        int numTickets = scanner.nextInt();

        List<Train> availableTrains = findTrainsWithAvailableSeats(origin, destination, numTickets);
        if (availableTrains.isEmpty()) {
            System.out.println("No trains available with enough seats.");
        } else {
            System.out.println("Available trains:");
            for (Train train : availableTrains) {
                train.displayTrainInfo();
            }
        }
    }

    private static void bookWithDynamicSeatAllocation(Scanner scanner) {
        System.out.println("Enter Origin City:");
        String origin = scanner.nextLine();

        System.out.println("Enter Destination City:");
        String destination = scanner.nextLine();

        System.out.println("Enter Number of Passengers (Max 10):");
        int numPassengers = scanner.nextInt();
        if (numPassengers < 1 || numPassengers > 10) {
            System.out.println("Please enter a valid number of passengers (1-10).");
            return;
        }

        List<Train> availableTrains = findTrainsWithAvailableSeats(origin, destination, numPassengers);
        if (availableTrains.isEmpty()) {
            System.out.println("No trains available with enough seats.");
            return;
        }

        Train selectedTrain = availableTrains.get(0); // Assume first train is chosen
        List<Integer> allocatedSeats = allocateSeats(selectedTrain, numPassengers);

        if (!allocatedSeats.isEmpty()) {
            System.out.println("Final Booking Summary:");
            System.out.println("Train ID: " + selectedTrain.getDepartureTime());
            System.out.println("Allocated Seats: " + allocatedSeats);
            System.out.println("Confirm booking? (yes/no)");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("yes")) {
                selectedTrain.allocateSeats(numPassengers, allocatedSeats);
                System.out.println("Booking confirmed. Departure time: " + selectedTrain.getDepartureTime());
            } else {
                System.out.println("Booking cancelled.");
            }
        } else {
            System.out.println("No adjacent seats available.");
        }
    }

    private static List<Integer> allocateSeats(Train train, int numSeats) {
        List<Integer> seats = new ArrayList<>();
        for (int i = 1; i <= train.getAvailableSeats(); i++) {
            if (seats.size() < numSeats) {
                if (!train.getAllocatedSeats().contains(i)) {
                    seats.add(i);
                }
            } else {
                break;
            }
        }

        // Check for adjacent seat allocation
        for (int i = 1; i <= train.getAvailableSeats() - numSeats + 1; i++) {
            boolean adjacentAvailable = true;
            for (int j = 0; j < numSeats; j++) {
                if (train.getAllocatedSeats().contains(i + j)) {
                    adjacentAvailable = false;
                    break;
                }
            }
            if (adjacentAvailable) {
                seats.clear();
                for (int j = 0; j < numSeats; j++) {
                    seats.add(i + j);
                }
                break;
            }
        }
        return seats;
    }

    private static User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private static List<Train> filterTrainsByRoute(String origin, String destination) {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getOrigin().equalsIgnoreCase(origin) && train.getDestination().equalsIgnoreCase(destination)) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }

    private static List<Train> findTrainsWithAvailableSeats(String origin, String destination, int numSeats) {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : filterTrainsByRoute(origin, destination)) {
            if (train.getAvailableSeats() >= numSeats) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }
}
