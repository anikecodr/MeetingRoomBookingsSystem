FROM openjdk:17-jre-slim-buster
EXPOSE 8081
ADD target/meetingRoomBooking-0.0.1-SNAPSHOT.jar.original meetingRoomBooking-0.0.1.jar
ENTRYPOINT ["java","-jar","/meetingRoomBooking-0.0.1.jar"]