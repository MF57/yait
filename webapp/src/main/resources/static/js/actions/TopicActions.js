import {UPVOTE_TOPIC} from "./types/TopicActionTypes";
export function upvoteTopic(id) {
    return {
        type: UPVOTE_TOPIC,
        id: id
    };
}