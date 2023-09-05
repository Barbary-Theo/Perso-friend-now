import React from 'react'
import '../style/main.css'
import FriendService from "../service/FriendService";
import ArcadeButton from "../component/ArcadeButton";

const Main = () => {

    FriendService.getAllFriends()
        .then((response) => {return response.json()})
        .then((friends) => console.log(friends))
        .catch((err) => console.error(err));

    return (
        <div className="test">
            <ArcadeButton text="Your are so sus" />
        </div>
    )
}

export default Main;