import React, {useEffect, useState} from 'react';
import '../style/components.css'
import FriendService from "../service/FriendService";

const CardFriend = ({friend, layer, nbRelations, index}) => {

    const [top, setTop] = useState("0vh");
    const [left, setLeft] = useState("0vw");

    const centerTop = window.innerHeight / 2;
    const centerLeft = window.innerWidth / 2;

    const distance =  window.innerHeight * 0.3 * layer

    useEffect(() => { setTop((centerTop + (distance * Math.sin((index  * (360/nbRelations)) * (Math.PI / 180)) * (0.8 + Math.random() * 0.4))) + "px"); setLeft((centerLeft + (distance * Math.cos((index  * (360/nbRelations)) * (Math.PI / 180)) * (0.8 + Math.random() * 0.4))) + "px") }, []);

    return (
        <div className="cardFriend" style={{"top": top, "left": left}}>
            {friend}
        </div>
    );
}

export default CardFriend;