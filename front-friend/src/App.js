import React, { Component } from 'react';
import './style/App.css';
import Main from './view/Main';
import Menu from "./component/Menu";
import DialogHello from "./component/DialogHello";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isDragging: false,
            startMouseX: 0,
            startMouseY: 0,
            startContainerX: 0,
            startContainerY: 0,
        };
        this.containerRef = React.createRef();
    }

    handleMouseDown = (e) => {
        this.setState({
            isDragging: true,
            startMouseX: e.clientX,
            startMouseY: e.clientY,
            startContainerX: this.getContainerX(),
            startContainerY: this.getContainerY(),
        });
    };

    handleMouseMove = (e) => {
        if (this.state.isDragging) {
            const offsetX = e.clientX - this.state.startMouseX;
            const offsetY = e.clientY - this.state.startMouseY;
            const newContainerX = this.state.startContainerX + offsetX;
            const newContainerY = this.state.startContainerY + offsetY;

            this.setContainerPosition(newContainerX, newContainerY);
        }
    };

    handleMouseUp = () => {
        this.setState({ isDragging: false });
    };

    getContainerX = () => {
        const style = getComputedStyle(this.containerRef.current);
        return parseFloat(style.left) || 0;
    };

    getContainerY = () => {
        const style = getComputedStyle(this.containerRef.current);
        return parseFloat(style.top) || 0;
    };

    setContainerPosition = (x, y) => {
        const container = this.containerRef.current;
        x =  x - 1;
        y = y - 1;

        container.style.left = `${x}px`;
        container.style.top = `${y}px`;
    };

    render() {
        return (
            <div
                onMouseDown={this.handleMouseDown}
                onMouseMove={this.handleMouseMove}
                onMouseUp={this.handleMouseUp}>
                <div
                    className="draggable-container"
                    ref={this.containerRef}>
                    <div className="content">
                        <Main />
                    </div>
                </div>
                <DialogHello />
                <Menu />
            </div>
        );
    }
}

export default App;
