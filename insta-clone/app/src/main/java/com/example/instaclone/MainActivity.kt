package com.example.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.instaclone.ui.theme.InstaCloneTheme
import kotlinx.coroutines.delay
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



data class User(
    val profile:String,
    val name:String
)

data class Story(
    val user: User,
    val story: String
)

data class Post(
    val user:User,
    val post: String,
    val description: String,
    val likesCount:Int,
    val commentsCount:Int,
    val isVideo: Boolean = false
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstaCloneTheme {
                val navController = rememberNavController()

                Surface(
                    modifier =  Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBar()
                        },
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ){ paddings ->
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(paddings)) {

                            NavHost(
                                navController = navController,
                                startDestination = "home" // Define the default start screen
                            ) {
                                composable("home") {
                                    HomeScreenContent(navController)
                                }
                                composable("search") {
                                    SearchScreen()
                                }
                                composable("add") {
                                    AddPost()
                                }
                                composable("reels") {
                                    ShowReels()
                                }
                            }
                            Modifier.padding(paddings)

                        }
                    }

                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(navController: NavController) { // Receive navController if needed

    val users = listOf(
        User(
            profile = "android.resource://com.example.instaclone/drawable/profile1",
            name = "Your Story"
        ),
        User(
            profile = "android.resource://com.example.instaclone/drawable/profile2",
            name = "Meesam"
        ),
        User(
            profile = "android.resource://com.example.instaclone/drawable/profile3",
            name = "Anwar Maqsood"
        ),
        User(
            profile = "android.resource://com.example.instaclone/drawable/profile4",
            name = "Muhammad Rafi"
        ),
        User(
            profile = "https://images.generated.photos/VEFNRr8_f-ZbZSGTxh5Cf8VLGOb1b2ts9UoF-Rlzeh4/rs:fit:256:256/czM6Ly9pY29uczgu/Z3Bob3Rvcy1wcm9k/LnBob3Rvcy92M18w/OTI0MDk2LmpwZw.jpg",
            name = "zack"
        ),
        User(
            profile = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKtZXVE-CUbAZl5P1IsH0oKJq3KaYQDXlruQ&s",
            name = "Asim"
        ),
        User(
            profile = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJMkBBQYqjV4HOfcyVBH0GsRDp5hIfznmpGA&s",
            name = "Meawking"
        )

    )



    val stories = listOf(
        Story(
            user = users[0],
            story = "android.resource://com.example.instaclone/drawable/story1"
        ),
        Story(
            user = users[1],
            story = "android.resource://com.example.instaclone/drawable/story2"
        ),
        Story(
            user = users[2],
            story = "android.resource://com.example.instaclone/drawable/story3"
        ),
        Story(
            user = users[3],
            story = "android.resource://com.example.instaclone/drawable/story2"
        ),
        Story(
            user = users[4],
            story = "android.resource://com.example.instaclone/drawable/story3"
        ),
        Story(
            user = users[5],
            story = "android.resource://com.example.instaclone/drawable/story3"
        ),Story(
            user = users[6],
            story = "android.resource://com.example.instaclone/drawable/story2"
        )
    )
    val posts = listOf(
        Post(
            user = users[1],
            post = "android.resource://com.example.instaclone/drawable/post1",
            description = "Weather in Karachi",
            likesCount = (100..10000).random(),
            commentsCount = (100..10000).random()
        ),
        Post(
            user = users[2],
            post = "android.resource://com.example.instaclone/drawable/post2",
            description = "Hill view",
            likesCount = (100..10000).random(),
            commentsCount = (100..10000).random()
        ),
        Post(
            user = users[3],
            post = "android.resource://com.example.instaclone/drawable/post3",
            description = "packed auditorium in FAST",
            likesCount = (100..10000).random(),
            commentsCount = (100..10000).random()
        ),
        Post(
            user = users[5],
            post = "android.resource://com.example.instaclone/drawable/reel1",
            description = "NU fest about to happen...",
            likesCount = (100..10000).random(),
            commentsCount = (100..10000).random(),
            isVideo = true
        )
    )


    Column(modifier = Modifier.fillMaxSize()) {
        Posts(users = users, posts = posts, stories = stories)
    }
}



@Composable
fun StoryViewer(
    stories: List<Story>,
    initialIndex: Int,
    onClose: () -> Unit
) {
    var currentIndex by remember { mutableStateOf(initialIndex) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount > 0) {
                        // Swipe right
                        if (currentIndex > 0) {
                            currentIndex--
                        }
                    } else {
                        // Swipe left
                        if (currentIndex < stories.size - 1) {
                            currentIndex++
                        }
                    }
                }
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(stories[currentIndex].story)
                .crossfade(400)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "Close",
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onClose()
                    }
                },
            tint = Color.White
        )

        Text(
            text = stories[currentIndex].user.name,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun Stories(
    users: List<User>,
    stories: List<Story>,
    onStoryClick: (Int) -> Unit
) {
    val context = LocalContext.current
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(users) { index, story ->
            if (index == 0) {
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures {
                        onStoryClick(index)
                    }
                }
            ) {
                val borderModifier = if (index == 0) {
                    Modifier.border(2.dp, Color.Gray, CircleShape)
                } else {
                    Modifier.border(
                        2.dp,
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xffff6f00),
                                Color(0xffffeb35),
                                Color(0xffff6f00),
                                Color(0xffff2b99),
                                Color(0xffff2bd1),
                                Color(0xffff2bd1),
                            )
                        ),
                        CircleShape
                    )
                }

                Box(
                    modifier = borderModifier
                        .size(70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(story.profile)
                            .crossfade(400)
                            .build(),
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = story.name, fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun Posts(
    users: List<User>,
    posts: List<Post>,
    stories: List<Story>
) {
    var showStoryViewer by remember { mutableStateOf(false) }
    var initialStoryIndex by remember { mutableStateOf(0) }

    if (showStoryViewer) {
        StoryViewer(
            stories = stories,
            initialIndex = initialStoryIndex,
            onClose = { showStoryViewer = false }
        )
    }

    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Stories(
                users = users,
                stories = stories,
                onStoryClick = { index ->
                    initialStoryIndex = index
                    showStoryViewer = true
                }
            )
        }
        items(posts) { post ->
            var liked by remember { mutableStateOf(false) }

            LaunchedEffect(liked) {
                if (liked) {
                    delay(2000)
                    liked = false
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 1.dp, horizontal = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .border(
                                1.dp, Brush.horizontalGradient(
                                    listOf(
                                        Color(0xffff6f00),
                                        Color(0xffffeb35),
                                        Color(0xffff6f00),
                                        Color(0xffff2b99),
                                        Color(0xffff2bd1),
                                        Color(0xffff2bd1),
                                    )
                                ), CircleShape
                            )
                            .size(33.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest
                                .Builder(context)
                                .data(post.user.profile)
                                .crossfade(400)
                                .build(),
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = post.user.name)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onDoubleTap = {
                        liked = true
                    })
                }, contentAlignment = Alignment.Center
            ) {



                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(post.post)
                            .crossfade(400)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )

                AnimatedVisibility(
                    visible = liked,
                    enter = scaleIn(
                        spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                    exit = scaleOut()
                ) {
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val iconsModifier = Modifier.size(20.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        modifier = iconsModifier,
                        painter = if (liked) {
                            painterResource(id = R.drawable.heart) // Filled heart
                        } else {
                            painterResource(id = R.drawable.ic_heart) // Empty heart
                        },
                        contentDescription = null,

                    )
                    Icon(
                        modifier = iconsModifier,
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = null
                    )
                    Icon(
                        modifier = iconsModifier,
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = null
                    )
                }
                Icon(
                    modifier = iconsModifier,
                    painter = painterResource(id = R.drawable.ic_save),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = "${post.likesCount} likes", fontSize = 13.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = post.description, fontSize = 13.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "View all ${post.commentsCount} comments", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}



@Composable
fun TopBar()
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 14.dp,
            vertical = 10.dp
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.height(40.dp),
            painter = painterResource(id = R.drawable.instagram),
        contentDescription = "instagram"
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                modifier = Modifier.size(23.dp),
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(20.dp))

            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.ic_msg),
                contentDescription = null
            )
        }

    }
}

@Composable
fun BottomBar(navController: NavController) {
    val context = LocalContext.current

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "Home",
            modifier = Modifier
                .size(22.dp)
                .clickable {
                    navController.navigate("home") // Navigate to home screen
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            modifier = Modifier
                .size(22.dp)
                .clickable {
                    navController.navigate("search") // Navigate to search screen
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = "Add",
            modifier = Modifier
                .size(22.dp)
                .clickable {
                    navController.navigate("add") // Navigate to search screen
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_ig),
            contentDescription = "Reels",
            modifier = Modifier
                .size(22.dp)
                .clickable {
                    navController.navigate("reels") // Navigate to search screen
                }
        )

        AsyncImage(
            modifier= Modifier
                .size(27.dp)
                .clip(CircleShape),
            model = ImageRequest
                .Builder(context)
                .data("android.resource://com.example.instaclone/drawable/profile1")
                .crossfade(400)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun SearchScreen() {
    // Add the content for the Search section here
    Text(text = "Search Screen", fontSize = 24.sp, modifier = Modifier.fillMaxSize())
}

@Composable
fun AddPost() {
    // Add the content for the Notifications section here
    Text(text = "Add posts", fontSize = 24.sp, modifier = Modifier.fillMaxSize())
}

@Composable
fun ShowReels () {
    // Add the content for the Profile section here
    Text(text = "Reels page", fontSize = 24.sp, modifier = Modifier.fillMaxSize())
}