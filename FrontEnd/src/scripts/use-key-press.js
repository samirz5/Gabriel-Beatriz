import {useState, useEffect} from 'react';

export default function useKeyPress(targetKey){
    // Keep state
    const [keyPressed, setKeyPressed] = useState(false);

    // If key is pressed
    function keydown({key}) {
        if ( key === targetKey){
            setKeyPressed(true);
            console.log('key down: ' + key)
        }
    }

    // If key is released
    function keyup({key}) {
        if (key === targetKey){
            setKeyPressed(false);
        }
    }

    // Event listeners
    useEffect(() => {
        window.addEventListener('keydown', keydown);
        window.addEventListener('keyup', keyup);
        // Remove event listeners on cleanup
        return () => {
            window.removeEventListener('keydown', keydown);
            window.removeEventListener('keyup', keyup);
        };
    }, []);

    return keyPressed;
}