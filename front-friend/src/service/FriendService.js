import Config from '../config.js';

class FriendService {

    static getAllFriends() {
        return fetch(Config.API + "/friend/all", {method: 'GET'});
    }

}

export default FriendService;