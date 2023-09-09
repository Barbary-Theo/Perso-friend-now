import React from 'react';
import '../style/menu.css';
import Icon from '@mdi/react';
import { mdiPlus, mdiMinus } from '@mdi/js';
import ArcadeButton from "./ArcadeButton";

const Menu = () => {
    return (
        <div className="menu">
            <ArcadeButton icon={<Icon path={mdiPlus} className="icon-menu" color='black'/>} text="Utilisateur" color="#95D39C" size="sm"/>
            <ArcadeButton icon={<Icon path={mdiMinus} className="icon-menu" color='black'/>} text="Utilisateur" color="#FCA6A5" size="sm"/>
            <ArcadeButton icon={<Icon path={mdiPlus} className="icon-menu" color='black'/>} text="Lien" color="#95D39C" size="sm"/>
            <ArcadeButton icon={<Icon path={mdiMinus} className="icon-menu" color='black'/>} text="Lien" color="#FCA6A5" size="sm"/>
        </div>
    );
}

export default Menu;