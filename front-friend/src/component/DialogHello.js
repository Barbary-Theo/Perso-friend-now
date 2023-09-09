import * as React from 'react';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import ArcadeButton from "./ArcadeButton";
import {useState} from "react";

const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

const DialogHello = () => {
    const [open, setOpen] = React.useState(true);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const [userFocused, setUserFocused] = useState('');

    const focusUser = () => {
        console.log(userFocused);
    }

    return (
        <div>
            <Dialog
                open={open}
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
                    <ArcadeButton event={focusUser} text="Attérir 🚀" size="lg"/>
                </div>
            </Dialog>
        </div>
    );
}

export default DialogHello;