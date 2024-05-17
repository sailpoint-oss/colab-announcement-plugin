/*
Project - Announcement
Version - 3.1
Author - Shandeep - https://www.linkedin.com/in/shandeepsrinivas/
*/

function announcementObserverForMutation(selector) {
	return new Promise(resolve => {
		if (document.querySelector(selector)) {
			return resolve(document.querySelector(selector));
		}

		const observer = new MutationObserver(mutations => {
			if (document.querySelector(selector)) {
				resolve(document.querySelector(selector));
				observer.disconnect();
			}
		});

		observer.observe(document.body, {
			childList: true,
			subtree: true
		});
	});
}

function announcementDisplay(toAnnounce) {
	if (toAnnounce) {
		announcementObserverForMutation('.padder-v').then((elm) => {
			jQuery('.padder-v').before(toAnnounce);
		});
	}
}

async function announcementExec() {
	const requestOptions = {
		method: 'GET',
		headers: {
			'X-XSRF-TOKEN': PluginHelper.getCsrfToken(),
		}
	};

	fetch(PluginHelper.getPluginRestUrl('AnnouncementPlugin/configure'), requestOptions).then(response => {
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		if (response.status == 200) {
			return response.json();
		}
	}).then(data => {
		announcementDisplay(data);
	}).catch(error => {
			console.error('Error:', error);
		});

}

announcementExec();