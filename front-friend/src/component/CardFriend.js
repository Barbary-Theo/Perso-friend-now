import React, {useEffect, useState} from 'react';
import '../style/components.css'
import FriendService from "../service/FriendService";

const CardFriend = ({friend}) => {

    const [top, setTop] = useState("0vh");
    const [left, setLeft] = useState("0vw");

    useEffect(() => { setTop((Math.floor(Math.random() * 100) + "vh")); setLeft((Math.floor(Math.random() * 100) + "vw")) }, []);

    return (
        <div className="cardFriend" style={{"top": top, "left": left}}>
            {friend}
        </div>
    );
}

export default CardFriend;