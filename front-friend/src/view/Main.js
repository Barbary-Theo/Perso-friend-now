import React, {useEffect, useState} from 'react'
import '../style/main.css'
import FriendService from "../service/FriendService";
import CardFriend from "../component/CardFriend";

const Main = ({friend}) => {

    return (
        <div className="container content">
            {friend != null ? <CardFriend key={friend.firstname} friend={friend.lastname} layer={0} nbRelations={friend.relations.length} index={0} ></CardFriend> : ''}
            {friend != null ? friend.relations.map((relation, index) => <CardFriend key={relation.relationFirstname} nbRelations={friend.relations.length} index={index} friend={relation.relationLastname} layer={1}></CardFriend>) : '' }
        </div>
    )
}

export default Main;