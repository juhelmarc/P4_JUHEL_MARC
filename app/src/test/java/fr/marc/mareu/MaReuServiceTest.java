package fr.marc.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.marc.mareu.DI.DI;
import fr.marc.mareu.dataservice.DummyMeetingApiGenerator;
import fr.marc.mareu.dataservice.MeetingApiService;
import fr.marc.mareu.model.Meeting;
import fr.marc.mareu.model.Room;
import fr.marc.mareu.model.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(JUnit4.class)
public class MaReuServiceTest {

    private MeetingApiService service;
    //TODO : dégager toute les variables
    // 1- expected
    // 2- appeler la fonction
    // 3- verrifier
    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }
    @Test
    public void getMeetingListNotFilteredWithSuccess() {
        // 1 - expected
        List<Meeting> expectedMeetingList = DummyMeetingApiGenerator.DUMMY_MEETING;
        // 2 - Test
        List<Meeting> meetingNotFiltered = service.getMeetingList();
        // 3 - Verify
        assertEquals(expectedMeetingList, meetingNotFiltered);
    }

    @Test
    public void deleteMeetingShouldRemoveMeeting() {
        // 1 - Expected
        Meeting expectedMeetingRemoved = DummyMeetingApiGenerator.DUMMY_MEETING.get(0);
        // 2 - Test
        service.deleteMeeting(expectedMeetingRemoved);
        // 3 -Verify
        assertFalse(service.getMeetingList().contains(expectedMeetingRemoved));
    }

    @Test
    public void bookMeetingWithSuccess() {
        // 1 - Expected
        Meeting meetingToBook = new Meeting(
                new Date(new Date().getTime()),
                new Date (new Date().getTime() + (45 * 60 * 1000)),
                new Room(2, "Room B"),
                "Peach",
                Arrays.asList( service.getUserList().get(0), service.getUserList().get(4))
                ,false );
        // 2 - Test
        service.bookMeeting(meetingToBook);
        // 3 - Verify
        assertTrue(service.getMeetingList().contains(meetingToBook));
        assertEquals(service.getMeetingList().size() , 8);

    }
    @Test
    public void getUserListWithSuccess() {
        // 1 - Expected
        List<User> expectedUserList = DummyMeetingApiGenerator.DUMMY_USERS;
        // 2 - Test
        List<User> userList = service.getUserList();
        // 3 - Verify
        assertEquals(expectedUserList, userList);
    }
    @Test
    public void getEmailInThisUserListWithSuccess() {
        // 1 - Expected
        List<String> expectedEmailList = new ArrayList<>();
        for(int index = 0 ; index < DummyMeetingApiGenerator.DUMMY_USERS.size() ; index++) {
           expectedEmailList.add(DummyMeetingApiGenerator.DUMMY_USERS.get(index).geteMail());
        }
        // 2 - Test
        List<String> emailList = service.getEmailInThisUserList(service.getUserList());
        // 3 - Verify
        assertEquals(expectedEmailList , emailList);
    }
    @Test
    public void getUserListInThisMeetingWithEmailList() {
        Meeting meetingToBook = new Meeting(
                new Date(new Date().getTime()),
                new Date (new Date().getTime() + (45 * 60 * 1000)),
                new Room(2, "Room B"),
                "Peach",
                Arrays.asList( service.getUserList().get(0), service.getUserList().get(4))
                ,false );
        service.bookMeeting(meetingToBook);
        // 1 - Expected
        List<User> expectedUserList = service.getMeetingList().get(service.getMeetingList().size() - 1).getUser();
        String emailInExpectedUserList = service.getUserList().get(0).geteMail() + "," + service.getUserList().get(4).geteMail();
        // 2 - Test

        List<User> userListInMeeting = service.getUserListInThisMeeting(emailInExpectedUserList);
        // 3 - Verify
        assertEquals(expectedUserList, userListInMeeting);
    }

    @Test
    public void getRoomListWithSuccess() {
        // 1 - Expected
        List<Room> expectedRoomList = DummyMeetingApiGenerator.DUMMY_ROOM;
        // 2 - Test
        List<Room> listRoom = service.getRoomList();
        // 3 - Verify
        assertEquals(expectedRoomList, listRoom);
    }

    @Test
    public void getRoomNameListWithSuccess() {
        // 1 - Expected
        List<String> expectedRoomNameList = new ArrayList<>();
        for(int index = 0 ; index < DummyMeetingApiGenerator.DUMMY_ROOM.size() ; index++) {
            expectedRoomNameList.add(service.getRoomList().get(index).getRoomName());
        }
        // 2 - Test
        List<String> roomNameList = service.getRoomNameList();
        // 3 - Verify
        assertEquals(expectedRoomNameList , roomNameList);
    }

    @Test
    public void applyAndGetRoomFilterWithSuccess() {
        // 1 - Expected
        List<Meeting> expectedMeetingListFilteredWithRoom = Arrays.asList(service.getMeetingList().get(0));
        // 2 - Test
        List<Meeting> meetingListFilteredWithRoom = service.applyRoomFilter(service.getMeetingList().get(0).getRoom());
        // 3 - Verify
        assertEquals(expectedMeetingListFilteredWithRoom , meetingListFilteredWithRoom);
    }

    @Test
    public void applyAndGetDateFilterWithSuccess() {
        // 1 - Expected
        String format = "MMM dd.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
        //the index 6 meeting of listMeeting is the only one how have a date 24 hours higher than others
        List<Meeting> expectedMeetingListFilteredWithDate = Arrays.asList(DummyMeetingApiGenerator.DUMMY_MEETING.get(6));
        String formatDateToFilter = simpleDateFormat.format(service.getMeetingList().get(6).getDate());
        // 2 - Test
        List<Meeting> meetingListFilteredWithDate = service.applyDateFilter(formatDateToFilter);
        // 3 - Verify
        assertEquals(expectedMeetingListFilteredWithDate, meetingListFilteredWithDate);
    }
}