import React, {useEffect, useState} from 'react'
import '../style/main.css'
import FriendService from "../service/FriendService";
import Menu from "../component/Menu";
import CardFriend from "../component/CardFriend";

const Main = () => {

    const [friends, setFriends] = useState([]);

    useEffect(() => {
        FriendService.getAllFriends()
            .then((response) => {return response.json()})
            .then((friends) => setFriends(friends))
            .catch((err) => console.error(err));
    });

    return (
        <div className="container">
            <Menu />
            { friends.map((friend) => <CardFriend key={friend.firstname} friend={friend.firstname}></CardFriend>) }
        </div>
    )
}

export default Main;