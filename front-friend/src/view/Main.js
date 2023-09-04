import React from 'react'
import '../style/main.css'
import FriendService from "../service/FriendService";

const Main = () => {

    FriendService.getAllFriends()
        .then((response) => {return response.json()})
        .then((friends) => console.log(friends))
        .catch((err) => console.error(err));

    return (
        <div className="test">
            <div>Among us</div>
        </div>
    )
}

export default Main;