package fr.marc.mareu.dataservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import fr.marc.mareu.model.Meeting;
import fr.marc.mareu.model.Room;
import fr.marc.mareu.model.User;

public class DummyMeetingApiService implements MeetingApiService {



    private List<Meeting> meetingList = DummyMeetingApiGenerator.generateMeetings();
    private List<User> userList =  DummyMeetingApiGenerator.generateUser();
    private List<Room> roomList = DummyMeetingApiGenerator.generateRoom();
    private List<Meeting> meetingListFiltered;

    @Override
    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingList.remove(meeting);
    }

    @Override
    public void bookMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public List<String> getEmailInThisUserList(List<User> thisUserList) {
        List<String> emailList = new ArrayList<>();
        for (User user : thisUserList) {
            emailList.add(user.geteMail());
        }
        return emailList;
    }

    @Override
    public List<User> getUserListInThisMeeting(String emailString) {
        List<User> userInMeeting = new ArrayList<>();
        List<String> emailList = Arrays.asList( emailString.replace(",", " ").split( " " ) );
        for (String email : emailList) {
            for (User user : userList) {
                if (email.equals( user.geteMail() )) {
                    userInMeeting.add( user );
                }
            }
        }
        if (userInMeeting.size() == 0) {
            return null;
        }
        return userInMeeting;
    }

    @Override
    public List<Room> getRoomList() {
        return roomList;
    }

    @Override
    public List<String> getRoomNameList() {
        List<String> roomNameList = new ArrayList<>();
        for(Room room : roomList) {
            roomNameList.add(room.getRoomName());
        }
        return roomNameList;
    }

    @Override
    public List<Meeting> applyRoomFilter(Room room) {
        meetingListFiltered = new ArrayList<>();
        if(room != null) {
            for (Meeting meeting : meetingList) {
                if(meeting.getRoom().getId() == room.getId()) {
                    meeting.setFiltered( true );
                    meetingListFiltered.add(meeting);
                } else {
                    meeting.setFiltered( false );
                }
            }
        }
        return meetingListFiltered;
    }

    @Override
    public List<Meeting>  applyDateFilter(String formatDate) {
        String format = "MMM dd.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
        meetingListFiltered = new ArrayList<>();

        for(Meeting meeting : meetingList)
            if(simpleDateFormat.format( meeting.getDate().getTime() ).equals( formatDate )) {
            //if(simpleDateFormat.format( meeting.getDate().getTime() ).equals( simpleDateFormat.format( date.getTime() ) )) {
               meeting.setFiltered( true );
               meetingListFiltered.add(meeting);
            } else {
                meeting.setFiltered(false);
            }
            return meetingListFiltered;
    }
}
