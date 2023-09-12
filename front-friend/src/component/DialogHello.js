import * as React from 'react';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import ArcadeButton from "./ArcadeButton";
import {useEffect, useState} from "react";
import FriendService from "../service/FriendService";

const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

const DialogHello = ({updateAfterSearch, loadingSearch, searchOpen}) => {
    const [userFocused, setUserFocused] = useState('');

    const handleClickSearch = () => {
        updateAfterSearch(userFocused);
    }

    const handleClose = () => {
        searchOpen = false;
    }

    return (
        <div>
            <Dialog
                open={searchOpen}
                TransitionComponent={Transition}
                keepMounted
                onClose={handleClose}
                aria-describedby="alert-dialog-slide-description"
                maxWidth="sm" fullWidth
            >
                <DialogTitle className="dialog-center">{"Bienvenu dans le * Monde * 🌍"}</DialogTitle>
                <div className="dialog-input-container">
                    <input value={userFocused} onChange={e => setUserFocused(e.target.value)} className="dialog-input" id="focused-user" placeholder="Utilisateur ciblé (nom, prénom, pseudo)"/>
                </div>
                <div className="dialog-btn-container">
                    <ArcadeButton loading={loadingSearch} event={handleClickSearch} text="Attérir 🚀" size="lg"/>
                </div>
            </Dialog>
        </div>
    );
}

export default DialogHello;