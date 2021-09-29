package fr.marc.mareu.dataservice;

import java.util.List;

import fr.marc.mareu.model.Meeting;
import fr.marc.mareu.model.Room;
import fr.marc.mareu.model.User;

public interface MeetingApiService {
//todo : enlever le boolean, avec applyRoomFilter et applyDateFilter renvoi une liste
    List<Meeting> getMeetingList();

    void deleteMeeting(Meeting meeting);

    void bookMeeting(Meeting meeting);

    List<User> getUserList();

    List<String> getEmailInThisUserList(List<User> thisUserList);

    List<User> getUserListInThisMeeting(String emailList);

    List<Room> getRoomList();

    List <String> getRoomNameList();

    List<Meeting> applyRoomFilter(Room room);

    List<Meeting>applyDateFilter(String formatDate);

}
