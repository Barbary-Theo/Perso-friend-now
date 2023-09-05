import React from 'react'
import '../style/main.css'
import FriendService from "../service/FriendService";
import ArcadeButton from "../component/ArcadeButton";
import Menu from "../component/Menu";

const Main = () => {

    FriendService.getAllFriends()
        .then((response) => {return response.json()})
        .then((friends) => console.log(friends))
        .catch((err) => console.error(err));

    return (
        <div className="test">
            <Menu />
            <ArcadeButton text="Your are so sus" size="lg"/>
        </div>
    )
}

export default Main;