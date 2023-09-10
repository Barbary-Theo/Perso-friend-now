import Config from '../config'

class FriendService {

    static getAllFriends() {
        return fetch(Config.API + "/friend/all", {method: 'GET'});
    }

    static getFriendByInfo(info) {
        return fetch(Config.API + "/friend/list/byValue?info=" + info, {method: 'GET'});
    }

}

export default FriendService;