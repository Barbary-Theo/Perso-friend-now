import React, {Component, useState} from 'react';
import './style/App.css';
import Main from './view/Main';
import Menu from "./component/Menu";
import DialogHello from "./component/DialogHello";
import FriendService from "./service/FriendService";

const App = () => {

    const [state, setState] = useState({
        isDragging: false,
        startMouseX: 0,
        startMouseY: 0,
        startContainerX: 0,
        startContainerY: 0,
    })
    let containerRef = React.createRef();

    const handleMouseDown = (e) => {
        setState({
            isDragging: true,
            startMouseX: e.clientX,
            startMouseY: e.clientY,
            startContainerX: getContainerX(),
            startContainerY: getContainerY(),
        });
    };

    const handleMouseMove = (e) => {
        if (state.isDragging) {
            const offsetX = e.clientX - state.startMouseX;
            const offsetY = e.clientY - state.startMouseY;
            const newContainerX = state.startContainerX + offsetX;
            const newContainerY = state.startContainerY + offsetY;

            setContainerPosition(newContainerX, newContainerY);
        }
    };

    const handleMouseUp = () => {
        setState({ isDragging: false });
    };

    const getContainerX = () => {
        const style = getComputedStyle(containerRef.current);
        return parseFloat(style.left) || 0;
    };

    const getContainerY = () => {
        const style = getComputedStyle(containerRef.current);
        return parseFloat(style.top) || 0;
    };

    const setContainerPosition = (x, y) => {
        const container = containerRef.current;
        x =  x - 1;
        y = y - 1;
        container.style.left = `${x}px`;
        container.style.top = `${y}px`;
    };

    const [loadingSearch, setLoadingSearch] = useState(false);
    const [friend, setFriend] = useState(null);
    const [searchOpen, setSearchOpen] = React.useState(true);

    const updateAfterSearch = (userFocused) => {
        setLoadingSearch(true)
        setTimeout(() => {
            FriendService.getFriendByInfo(userFocused)
                .then((response) => {
                    if(response.status === 200) return response.json();
                    throw response.status;
                })
                .then((friendFocused) => {
                    setFriend(friendFocused)
                    console.log(friendFocused)
                    setSearchOpen(false);
                })
                .catch((err) => console.error(err))
                .finally(() => setLoadingSearch(false));
        }, 1000)
    }

    return (
        <div
            onMouseDown={handleMouseDown}
            onMouseMove={handleMouseMove}
            onMouseUp={handleMouseUp}>
            <div
                className="draggable-container"
                ref={containerRef}>
                <div className="content">
                    <Main friend={friend} />
                </div>
            </div>
            <DialogHello searchOpen={searchOpen} updateAfterSearch={updateAfterSearch} loadingSearch={loadingSearch}/>
            <Menu />
        </div>
    );
}

export default App;
