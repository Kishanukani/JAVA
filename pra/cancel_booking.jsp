<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cancel Booking</title>
        <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to CSS file -->
    </head>

    <body>
        <div class="container">
            <h2>Cancel Booking</h2>
            <form action="" method="post">
                <label for="bookingId">Booking ID:</label>
                <input type="number" id="bookingId" name="bookingId" required><br>
                <input type="submit" value="Cancel Booking">
            </form>
        </div>

        <!-- Display confirmation message if available -->
        <% String message=(String) request.getAttribute("message"); if (message !=null) { %>
            <script>
                alert("<%= message %>");
            </script>
            <% } %>
    </body>

    </html>