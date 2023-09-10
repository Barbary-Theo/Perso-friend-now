import React from 'react';
import '../style/components.css';
import Icon from '@mdi/react';
import { mdiLoading } from '@mdi/js';

const ArcadeButton = ({icon, text, size, color, event, loading}) => {

    const fontSize = size === "xs" ? "5px"
                            : size === "md" ? "10px"
                            : size === "lg" ? "15px"
                            : "10px";
    const padding = size === "xs" ? "3px 6px"
                            : size === "md" ? "7px 15px"
                            : size === "lg" ? "12px 25px"
                            : "7px 15px";

    return (
        <a onClick={event} className="btn-arcade flex-center" style={{"backgroundColor": color, "padding": padding, "fontSize": fontSize}}>
            <div className="flex-center">{icon}</div>
            {!loading ? text : <Icon path={mdiLoading} className="rotating" color='black'/>}
        </a>
    )
}

export default ArcadeButton;
